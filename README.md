# A* Treasure Hunt

## 📌 Description

This project implements the **A* (A-Star) Search Algorithm** in Java to find the shortest path from a starting position to a treasure in a grid-based maze.

The maze can contain **danger zones (blocked cells)** that the algorithm must avoid while searching for the shortest path.

The program calculates the path using the A* evaluation function:

**f(n) = g(n) + h(n)**

where:

- `g(n)` = Cost from the starting position to the current node
- `h(n)` = Estimated cost from the current node to the treasure
- `f(n)` = Total estimated cost

The program uses the **Euclidean distance** as its heuristic. The heuristic is calculated using the distance between the current position and the treasure. 

## 🚀 Features

- Finds the shortest path to a treasure.
- Uses the A* search algorithm.
- Supports customizable maze size.
- Allows the user to specify the starting position.
- Allows the user to specify the treasure position.
- Supports multiple danger zones.
- Avoids blocked/dangerous cells.
- Displays the complete path.
- Displays the total path cost.
- Reports when no path exists.

## 🧠 Algorithm

### A* Search Algorithm

A* combines the actual cost of reaching a node with an estimated cost to reach the goal.

```text
f(n) = g(n) + h(n)
