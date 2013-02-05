Experimentations with Coursera's Algorithms. No private materials, just from lectures.

# Union-Find
Algorithm to find if two elements of a superset belong to one subset element, which is formed by making
`union` operation on two elements. Another view of it is that elements are nodes in some net, and algorithm
should say, whether we have a path, connecting two given nodes.

## Number of elements N=100000, number of union operations NU=50000, number of tests for connection NC=50000
* path compression quick-union: 33,00 ms
* quick-union: 68,00 ms - *surprisingly weighted unions behind of quick-union?*
* path compression weighted quick-union: 74,00 ms
* weighted quick-union: 76,00 ms
* quick-find: 76550,00 ms

## N=100000, NU=100000, NC=50000
* path compression quick-union: 29,00 ms
* path compression weighted quick-union: 58,00 ms
* weighted quick-union: 61,00 ms
* quick-union: 69,00 ms
* quick-find: 80167,00 ms

## N=100000, NU=100000, NC=100000
* path compression quick-union: 49,00 ms
* path compression weighted quick-union: 57,00 ms
* quick-union: 75,00 ms
* weighted quick-union: 88,00 ms
* quick-find: 83748,00 ms

It seems that path compression gives the very significant benefit, given that it's one additional line of code
over quick-union.