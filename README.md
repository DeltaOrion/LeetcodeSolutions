# LeetCode Solutions

This repository contains my solutions to various problems on LeetCode in Java.

## Structure
The solutions are organized by category and can be found in their respective directories. Each problem has its own class named after the problem number, for example Problem34.java.

The repository is structured as follows:

```txt
me.jacob.assign.algorithms/
├── array/
│   ├── Problem1.java
│   ├── Problem2.java
│   ├── ...
├── backtracking/
│   ├── Problem22.java
│   ├── Problem46.java
│   ├── ...
├── dynamic-programming/
│   ├── Problem5.java
│   ├── Problem53.java
│   ├── ...
├── ...
```

Each problem class contains the problem statement, my approach, and the code. The code is well commented and formatted for readability.

## Contributing

I welcome any contributions to this repository! If you have a more optimal solution, have found a bug in one of my solutions, or would like to add a solution to a problem that I haven't covered yet, please feel free to open a pull request.

## Building 

To build the program, run the following commands in the source directory.

Windows
```cmd
mkdir out
javac -d out src/**/*.java
```

Bash
```sh
mkdir out
find src -name "*.java" -exec javac -d out {} +
```

## Running

Once built you can run any of the problem as follows. Don't forget to put a `public static void main` to run the class. 

```sh
# Problem 119
java -cp out me.jacob.assign.algorithms.Problem119

# Problem 11
java -cp out me.jacob.assign.algorithms.array.Problem11
```