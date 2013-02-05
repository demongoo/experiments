Experimentations with Coursera's Algorithms. No private materials, just from lectures.

# Union-Find
Algorithm to find if two elements of a superset belong to one subset element, which is formed by making
`union` operation on two elements. Another view of it is that elements are nodes in some net, and algorithm
should say, whether we have a path, connecting two given nodes.

## Using a big data file largeUF.txt (~27 Mb, N=1000000)
* path compression weighted quick-union: 1081,00 ms
* path compression quick-union: 1461,00 ms
* weighted quick-union: 12026,00 ms
* quick-union: `stackoverflow` in `root`
* quick-find: ages

## Using random data, N=100000, union operations NU=100000
* path compression quick-union: 55,00 ms
* path compression weighted quick-union: 108,00 ms
* weighted quick-union: 109,00 ms
* quick-union: 4103,00 ms
* quick-find: 140054,00 ms

## Using random data, N=100000, union operations NU=200000
* path compression quick-union: 79,00 ms
* weighted quick-union: 92,00 ms
* path compression weighted quick-union: 92,00 ms
* quick-union: 34231,00 ms
* quick-find: 166751,00 ms