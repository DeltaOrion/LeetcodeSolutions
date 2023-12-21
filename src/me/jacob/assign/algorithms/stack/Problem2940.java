package me.jacob.assign.algorithms.stack;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem2940 {

    public static void main(String[] args) {
        Problem2940 p = new Problem2940();
        var res = p.leftmostBuildingQueries(new int[]{1, 2, 1, 2, 1, 2}, new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5}, {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5}, {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 4}, {5, 5}});
        System.out.println(Arrays.toString(res));
    }

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int[] result = new int[queries.length];
        PriorityQueue<Query> queryQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.minIndex, o1.minIndex));

        int count = 0;
        for (int[] query : queries) {
            int buildingAlice = query[0];
            int buildingBob = query[1];

            if(buildingAlice == buildingBob) {
                result[count] = buildingAlice;
            } else {
                queryQueue.add(new Query(Math.max(heights[buildingAlice], heights[buildingBob]), Math.max(buildingAlice, buildingBob), count));
            }
            count++;
        }

        Building[] stack = new Building[heights.length];
        int head = -1;

        for (int i = heights.length - 1; i >= 0; i--) {
            while (head >= 0 && stack[head].height <= heights[i]) {
                stack[head] = null;
                head--;
            }

            head++;
            stack[head] = new Building(heights[i], i);

            while (!queryQueue.isEmpty() && queryQueue.peek().minIndex == i) {
                Query query = queryQueue.poll();
                int left = 0;
                int right = head;
                result[query.resultIndex] = -1;

                while (left <= right) {
                    int middle = (left + right) / 2;
                    Building building = stack[middle];
                    if (building.height <= query.minHeight) {
                        right = middle - 1;
                    } else if (middle == head || stack[middle + 1].height <= query.minHeight) {
                        result[query.resultIndex] = building.index;
                        break;
                    } else {
                        left = middle + 1;
                    }
                }
            }
        }

        return result;
    }

    private static class Query {
        private int minHeight;
        private int resultIndex;
        private int minIndex;

        public Query(int minHeight, int minIndex, int resultIndex) {
            this.minHeight = minHeight;
            this.minIndex = minIndex;
            this.resultIndex = resultIndex;
        }
    }

    private static class Building {
        private int height;
        private int index;

        public Building(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }
}
