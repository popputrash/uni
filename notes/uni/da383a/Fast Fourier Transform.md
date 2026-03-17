---
course: da383a
topic: Untitled
tags:
  - lecture
  - dsp
  - fourier
date: Monday, February 16th 2026, 8:26:34 am
updated: Monday, February 16th 2026, 8:33:16 am
aliases:
  - fft
---
# Twiddle Factors
Is it possible to calculate [[Discrete Fourier Transform]] in a more efficient way then $O(N²)$
$$
W_N = e^{-j\frac{2\pi}{N}} \to
$$
## Symmetry
$$
W_{N}^{k+ N/2} = -W_{N}^k
$$
## Periodicity
$$
W_{N}^{k+N}=W_{N}^k
$$
## Recursion 
$$
W_{N}^{2} = W_{\frac{N}{2}}
$$

# Fast Fourier Transform

## Recursive Algorithm
- $N = min(2^m|2^m\geq L)$
- Follows divide and conquer strategy
- Recursively breaks down N-point [[Discrete Fourier Transform]] into smaller $\frac{N}{2}$-point [[Discrete Fourier Transform]]s. This continues until it reaches the best case of N = 1.


## Iterative Algorithm
- To reduce memory usage and enable faster computation (since recursive algorithms require waiting for recursive calls to complete)
- $N = min(2^m|2^m\geq L)$
- Steps 
	1. Recover inputs using Bit-Reversal
	   
	2. 

