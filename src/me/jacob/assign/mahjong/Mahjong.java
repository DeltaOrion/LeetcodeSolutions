package me.jacob.assign.mahjong;

import java.util.ArrayList;
import java.util.List;

public class Mahjong {

    private static final int SEQUENCE_LENGTH = 3;
    private static final int PAIR_LENGTH = 2;

    public static void main(String[] args) {
        Mahjong m = new Mahjong();
        List<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(Suite.Characters, 1));
        tiles.add(new Tile(Suite.Characters, 1));
        tiles.add(new Tile(Suite.Characters, 1));
        tiles.add(new Tile(Suite.Bamboo, 2));
        tiles.add(new Tile(Suite.Bamboo, 3));
        tiles.add(new Tile(Suite.Bamboo, 4));
        tiles.add(new Tile(Suite.Bamboo, 5));
        tiles.add(new Tile(Suite.Bamboo, 6));
        tiles.add(new Tile(Suite.Bamboo, 6));
        tiles.add(new Tile(Suite.Bamboo, 6));
        tiles.add(new Tile(Suite.Dots, 7));
        tiles.add(new Tile(Suite.Dots, 8));
        tiles.add(new Tile(Suite.Dots, 9));

        System.out.println(m.isTenpai(tiles));
    }

    public boolean isTenpai(List<Tile> tiles) {
        //1. Group the tiles into their respective suites
        List<Integer>[] initialGroupings = new List[Suite.values().length];
        for (int i = 0; i < initialGroupings.length; i++) {
            initialGroupings[i] = new ArrayList<>();
        }

        for (Tile tile : tiles) {
            initialGroupings[tile.suite.ordinal()].add(tile.number);
        }

        for (List<Integer> grouping : initialGroupings) {
            grouping.sort(Integer::compare);
        }

        //The readings list is an important data structure for determining whether our hand is in Tenpai.
        //It essentially stores our current way of "interpreting" the hand. For example if we have the hand
        // [Characters, 1,1,1], [Bamboo, 2,3,4,5,6,6,6], [Dots, 7,7,7] we could interpret the bamboo in a number
        //of ways. We could determine it as [[2,3,4], [5], [6,6,6]] or [[2,3], [4,5,6], [6,6]] etc.
        //The readings list does not care about the suite that a tile belongs but any method writing to the data structure
        //should be careful to not mix suites so tiles together. For instance, if in the above example we interpreted the bamboo
        //as 2,3,4, 5, 6,6,6 we would add the characters and dots lists to the reading to form [[1,1,1], [2,3,4], [5], [6,6,6], [7,7,7]]
        List<List<Integer>> readings = new ArrayList<>();
        List<List<Integer>> largeGroupings = new ArrayList<>();

        //sort the groupings, add readings less than the sequence length directly to the readings list. Otherwise
        //add it to the large groupings list so we can split them up later.
        for (List<Integer> grouping : initialGroupings) {
            if (grouping.size() <= SEQUENCE_LENGTH && grouping.size() >= 1) {
                if (!verifySequence(grouping)) {
                    return false;
                }

                readings.add(grouping);
            } else if (grouping.size() >= SEQUENCE_LENGTH) {
                largeGroupings.add(grouping);
            }
        }

        //determine the total number of readings we need to find from here.
        int x = numberOfSequences(tiles.size());
        x -= readings.size();

        //split the large groupings.
        if (largeGroupings.size() > 0) {
            return generateReadings(readings, largeGroupings, largeGroupings.get(0).size(), x, 0, 0);
        }

        return false;
    }

    private boolean verifySequence(List<Integer> grouping) {
        if (grouping.size() == 0 || grouping.size() == 1) {
            return true;
        }

        return checkAscending(grouping) || checkEqual(grouping);
    }

    /**
     * Verifies whether a reading list is in tenpai.
     */
    public boolean verifyReading(List<List<Integer>> readings) {
        //There are two possible cases
        // - 1. We are missing one pair but everything else forms correct sequences
        // - 2. We are missing one sequence but we have a correct pair and everything else forms a pair
        // - 3. Neither in which case we are not in tenpai.
        int completeSequences = 0;
        int pairs = 0;
        int incompleteSequences = 0;
        int incompletePairs = 0;

        int x = readings.size();

        for (List<Integer> reading : readings) {
            if (reading.size() == 1) {
                incompletePairs++;
            } else if (checkAscending(reading)) {
                if (reading.size() == SEQUENCE_LENGTH) {
                    completeSequences++;
                } else if (reading.size() == SEQUENCE_LENGTH - 1) {
                    incompleteSequences++;
                } else {
                    return false;
                }
            } else if (checkEqual(reading)) {
                if (reading.size() == PAIR_LENGTH) {
                    pairs++;
                } else if(reading.size() == SEQUENCE_LENGTH) {
                    completeSequences++;
                } else {
                    return false;
                }
            }
        }

        boolean missingGroup = completeSequences == (x - 1) && incompletePairs == 1;
        boolean missingSequence = pairs == 1 && incompleteSequences == 1 && completeSequences == x - 2;

        if(missingGroup || missingSequence) {
            //just print out the interpretations which caused a hand to be in tenpai for now.
            System.out.println(readings);
            return true;
        }

        return false;
    }

    //Check whether a sequence is in ascending order
    private boolean checkAscending(List<Integer> grouping) {
        int prev = grouping.get(0);
        for (int i = 1; i < grouping.size(); i++) {
            if (grouping.get(i) != prev + 1) {
                return false;
            }

            prev = grouping.get(i);
        }

        return true;
    }

    //check whether all the elements in a sequence are equal
    private boolean checkEqual(List<Integer> grouping) {
        int prev = grouping.get(0);
        for (int i = 1; i < grouping.size(); i++) {
            if (grouping.get(i) != prev) {
                return false;
            }
        }

        return true;
    }

    private int numberOfSequences(int n) {
        return (n / SEQUENCE_LENGTH) + 1;
    }

    /**
     * @param n The number of tiles left to split
     * @param x The number of sets left to split it
     * @param cgIndex The index of where we are up to in the currently selected grouping
     * @param gIndex The index of the currently selected grouping.
     * @return Whether the groupings are in tenapi.
     *
     *  Generates every valid interpretation of a list of groupings given it needs to be split into x sequences. This method will early terminate
     *  if it finds that a sequence it is about to form will not be in ascending or all elements equal.
     *
     *  This method uses backtracking, it will first try splitting off 1 element, 2 elements 3 elements etc. Once it has finished partitioning a grouping it will move onto the
     *  next grouping until there are no more groupings left to
     */
    public boolean generateReadings(List<List<Integer>> readings, List<List<Integer>> groupings, int n, int x, int cgIndex, int gIndex) {
        //early terminate if it is not possible to continue splitting up. If we can't remove sequence length for the remaining
        //sets then it is impossible to keep going.
        if (n > SEQUENCE_LENGTH * x) {
            return false;
        }

        //If we removed more elements then possible leave.
        if (n < 0) {
            return false;
        }

        //If we have used up every element then move onto the next grouping if possible.
        if (n == 0) {
            if (gIndex == groupings.size() - 1) {
                return verifyReading(readings);
            }

            return generateReadings(readings, groupings, groupings.get(gIndex).size(), x, 0, gIndex + 1);
        }

        List<Integer> currentGrouping = groupings.get(gIndex);
        List<Integer> reading = new ArrayList<>();
        readings.add(reading);
        boolean works = false;

        //try splitting off 0, 1, 2, 3 elements.
        for (int i = 0; i <= SEQUENCE_LENGTH; i++) {
            //if we are removing too many elements do not continue.
            if (cgIndex + i > currentGrouping.size()) {
                readings.remove(readings.size() - 1);
                return false;
            }

            if (i > 0) {
                reading.add(currentGrouping.get(cgIndex + i - 1));
            }

            //if we form a valid sequence add it to the list and continue generating with 1 less set, and i elements removed.
            if (verifySequence(reading)) {
                if(generateReadings(readings, groupings, n - i, x - 1, cgIndex + i, gIndex)) {
                    works = true;
                }
            } else {
                readings.remove(readings.size() - 1);
                return false;
            }
        }

        readings.remove(readings.size() - 1);
        return works;
    }

    private static class Tile {
        Suite suite;
        int number;

        public Tile(Suite suite, int n) {
            this.suite = suite;
            this.number = n;
        }
    }

    private enum Suite {
        Bamboo,
        Characters,
        Dots
    }
}
