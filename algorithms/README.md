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

## Using random data, N=100, No value limit
* shell: 2,00 ms
* bottom-up merge: 2,00 ms
* merge: 4,00 ms
* insertion: 4,00 ms
* 3-way quick: 14,00 ms
* quick: 28,00 ms
* selection: 65,00 ms

Clearly seen which algorithm has much overhead. Except selection, it's just slow.

## Using random data, N=1000, No value limit
* 3-way quick: 3,00 ms
* bottom-up merge: 19,00 ms
* shell: 20,00 ms
* merge: 25,00 ms
* quick: 36,00 ms
* insertion: 49,00 ms
* selection: 113,00 ms

## Using random data, N=100000, No value limit
* 3-way quick: 152,00 ms
* bottom-up merge: 183,00 ms
* quick: 238,00 ms
* shell: 279,00 ms
* merge: 299,00 ms
* selection: 181940,00 ms
* insertion: 185905,00 ms

## Using random data, N=100000, Max=50000 (means that ~2 duplicates for each key)
* 3-way quick: 134,00 ms
* bottom-up merge: 179,00 ms
* quick: 191,00 ms
* shell: 272,00 ms
* merge: 300,00 ms
* selection: 183698,00 ms
* insertion: 183954,00 ms

## Using random data, N=100000, Max=10000 (means that ~10 duplicates for each key)
* 3-way quick: 123,00 ms
* bottom-up merge: 181,00 ms
* quick: 199,00 ms
* shell: 268,00 ms
* merge: 300,00 ms
* selection: 185104,00 ms
* insertion: 185363,00 ms

## Using random data, N=100000, Max=100 (means that ~1000 duplicates for each key)
* 3-way quick: 56,00 ms
* shell: 129,00 ms
* bottom-up merge: 157,00 ms
* quick: 266,00 ms
* merge: 279,00 ms
* selection: 156062,00 ms
* insertion: 156903,00 ms

Seems just my laptop had sped up :)

## Using presorted array, N=100000, no duplicates
* insertion: 14,00 ms
* shell: 57,00 ms
* bottom-up merge: 193,00 ms
* 3-way quick: 201,00 ms
* quick: 281,00 ms
* merge: 302,00 ms
* selection: 219002,00 ms

## Using reversed array, N=100000, no duplicates
* shell: 95,00 ms (why ?!)
* bottom-up merge: 166,00 ms
* 3-way quick: 183,00 ms
* quick: 226,00 ms
* merge: 253,00 ms
* selection: 186045,00 ms
* insertion: 383335,00 ms

## Using array of ones, N=100000
* shell: 95,00 ms (why ?!)
* bottom-up merge: 166,00 ms
* 3-way quick: 183,00 ms
* quick: 226,00 ms
* merge: 253,00 ms
* selection: 186045,00 ms
* insertion: 383335,00 ms

## Using array of 1,2,1,2..., N=100000
* 3-way quick: 38,00 ms
* shell: 50,00 ms
* bottom-up merge: 148,00 ms
* quick: 197,00 ms
* merge: 284,00 ms
* insertion: 83461,00 ms
* selection: 141924,00 ms