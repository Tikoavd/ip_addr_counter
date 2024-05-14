solution for problem:
Calculate the number of unique addresses in large file using as little memory and time as possible.

I used an integers array with 8 * 256 * 256 * 256 elements. Each element is an integer with 32 bits.
IPV4 addresses can contain 4 numbers, each number range is 0-255.
That means, number of probable unique IPV4 addresses is 256 ^ 4.
Each element of array contains info whether file contains this ip address or not based on its index and value.
For example: 0 index of array is an integer, that responsible for n*8.0.0.0 (n range is 0-31) (0.0.0.0, 8.0.0.0, 16.0.0.0 etc.).
Info about containing is a bit (0 if not, and 1 if found), bit index based on n. first bit is responsible for 0.0.0.0, second bit for 8.0.0.0 etc.
Algorithm iterates over file once and finds unique IPV4 addresses on it using 512MB (size of integers array) memory. 
Algorithm complexity is O(n) where n is a number of lines in file.
