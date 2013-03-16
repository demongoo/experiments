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

# Sort
Plain old sorting algorithms.

## Using random data, N=1000, No value limit
* quick: 0.89 ms
* 3-way quick: 1.05 ms
* shell: 1.17 ms
* heap: 1.21 ms
* bottom-up merge: 1.30 ms
* merge: 1.75 ms
* insertion: 21.53 ms
* selection: 31.71 ms

## Using random data, N=100000, No value limit
* quick: 114 ms
* 3-way quick: 162 ms
* heap: 194 ms
* bottom-up merge: 212 ms
* merge: 216 ms
* shell: 277 ms
* insertion: 187691 ms
* selection: 209767 ms

## Using random data, N=100000, Max=50000 (~2 duplicates for each key)
* 3-way quick: 149 ms
* bottom-up merge: 188 ms
* heap: 191 ms
* merge: 201 ms
* quick: 206 ms
* shell: 278 ms
* insertion: 186661 ms
* selection: 208374 ms

## Using random data, N=100000, Max=10000 (~10 duplicates for each key)
* quick: 113 ms
* 3-way quick: 133 ms
* bottom-up merge: 181 ms
* heap: 193 ms
* merge: 202 ms
* shell: 257 ms
* insertion: 185974 ms
* selection: 208700 ms

## Using random data, N=100000, Max=100 ( ~1000 duplicates for each key)
* 3-way quick: 53 ms
* quick: 85 ms
* shell: 101 ms
* heap: 140 ms
* bottom-up merge: 155 ms
* merge: 182 ms
* insertion: 153643 ms
* selection: 175898 ms

## Using presorted array, N=100000, no duplicates
* insertion: 3 ms
* shell: 35 ms
* quick: 114 ms
* 3-way quick: 156 ms
* heap: 178 ms
* bottom-up merge: 181 ms
* merge: 192 ms
* selection: 206238 ms

## Using reversed array, N=100000, no duplicates
* shell: 86 ms
* quick: 115 ms
* 3-way quick: 152 ms
* heap: 163 ms
* merge: 174 ms
* bottom-up merge: 178 ms
* selection: 211727 ms
* insertion: 374394 ms (worst case)

## Using array of ones, N=100000
* insertion: 3 ms
* heap: 11 ms
* 3-way quick: 21 ms
* shell: 27 ms
* quick: 87 ms
* bottom-up merge: 131 ms
* merge: 158 ms
* selection: 177962 ms

## Using array of 1,2,1,2..., N=100000
* 3-way quick: 23 ms
* shell: 39 ms
* heap: 81 ms
* quick: 89 ms
* bottom-up merge: 169 ms
* merge: 178 ms
* insertion: 79034 ms
* selection: 175025 ms

## Using random data, N=5000000, No value limit
* bottom-up merge: 12849 ms
* merge: 12875 ms
* quick: 14976 ms
* 3-way quick: 16871 ms
* heap: 17044 ms
* shell: 36395 ms
* insertion: ... (still running)
* selection: ... (still running) :)