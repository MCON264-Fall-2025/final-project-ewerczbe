# Event Planner Mini

This project demonstrates practical use of data structures:
linked lists, stacks, queues, maps, trees, sorting, and searching.

## What You Must Do
- Implement all TODO methods
- Write JUnit 5 tests for core logic
- Pass instructor autograding tests
- Explain your design choices in this README

See Canvas assignment for full requirements.
In this project, I relied on a set of fundamental data structures that offered both simplicity and efficiency for the tasks I needed to perform. For managing guests and venues, I chose to use ArrayLists. This structure made it easy to add or remove items dynamically, and it provided a straightforward way to iterate through the data whenever I needed to access or update information. For handling tasks that needed to be completed in a specific order, I implemented a Queue, which naturally supports first‑in, first‑out processing. This ensured that tasks were addressed in the exact sequence they were added, which felt like the most rational approach for maintaining workflow consistency.
To support undo functionality, I incorporated a Stack. Since a stack operates on a last‑in, first‑out basis, it allowed me to reverse actions in the opposite order they were performed. This made it ideal for features where the most recent change should be undone first. For organizing the seating chart, I used a HashMap, mapping each table number to a list of assigned guests. This structure provided fast access and a clean way to associate groups of guests with specific tables.
When it came to searching, I used linear search throughout the project. Whether I was looking for a particular guest or selecting a venue, scanning through the list sequentially was sufficient given the project’s scale. Since no features required ordering the data, I didn’t implement any sorting algorithms.
In terms of Big‑O complexity, most operations ran in O(n) time. Finding a guest required scanning the entire list, selecting a venue followed the same pattern, and generating the seating arrangement involved looping through all guests once. Although these operations are linear, they were efficient enough for the project’s needs and aligned well with the chosen data structures.


