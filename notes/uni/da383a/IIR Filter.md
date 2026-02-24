---
course: da383a
topic: Untitled
tags:
  - lecture
  - dsp/filters
  - dsp/iir
date: Monday, February 16th 2026, 8:26:34 am
updated: Monday, February 16th 2026, 10:50:26 am
aliases:
  - IIR
jupyter:
  jupytext:
    cell_metadata_filter: -all
    formats: ipynb,md
    text_representation:
      extension: .md
      format_name: markdown
      format_version: '1.3'
      jupytext_version: 1.18.1
  kernelspec:
    display_name: Python 3 (ipykernel)
    language: python
    name: python3
---
# How are poles and zeroes of $H(z)$ influencing $|(H(e^{j\omega})|$
## Reminders
- For IIR Filters
  $y[n] = \sum a_{k}y[n-k]0\sum b_{k}x[n-k]$ -> Z-Transform $h(z)=\frac{y(z)}{x(z)}$ fyll ut från föreläsning

- $|H(e^{i\omega})|=H(z)|\to z=e^{j\omega}$

Lets assume that our $H(z)$ is written in the following form
$$
H(z) = G_1 \frac{(z-z_{1})(z-z_{2})\dots(z-z_{M})}{(z-p_{1})(z-p_{2})\dots(z-p_{N})}
\to 
|H(e^{j\omega})| =G_1 \frac{(e^{j\omega}-z_{1})(e^{j\omega}-z_{2})\dots(e^{j\omega a}-z_{M})}{(e^{j\omega}-p_{1})(e^{j\omega}-p_{2})\dots(e^{j\omega}-p_{N})}
$$
For simplicity, first a single zero or pole that we call Type equation here. $v_{k}$(since it can be either $z_{k}$ and $p_{k}$) and $|e^{j\omega}-v_{k}|$ is of interest.

A zero close to the unit circle will attenuate $|H(e^{j \omega})|$ near it location (at angular frequencies near its phase). The closer the stronger the attenuation (at unit circle: $|H(e^{j \omega})| = 0$)
A pole close to the unit circle will amplify $H(e^{e\omega})$ near its location. The closer the stronger the amplification.

## Example
Draw te pole-zero locations for $H(z)$ and try to figure out where 
$H(e^{j\omega})$ is large and small when
$$
H(z) = 1-\frac{z^{-z}}{1-z^{-1}+\frac{1}{2}z^{-2}}
$$
First break out the highest power of $z^{-1}$ in numerator and denominator

$$
H(z) = \frac{z^{-1}}{z^{-2}}z\frac{z-1}{z²-z+\frac{1}{2}}=z \to z\frac{z-1}{z²-z+\frac{1}{2}}
$$
The first factor contributes with a zero at $z_{1} = 0$ and the numerator of the fraction another on at $z_{2} = 1$

Finding the roots of the second degree polynomial in the denominator gives to two conjugate poles at $p_{1}=0.5(1+j)$ and $p_2 = 0.5(1-j)$

## Pole-Zero diagram
![[Pasted image 20260216105214.png]]

![[Pasted image 20260216110442.png]]

# Designing IIR Filters
- Influence of the poles and zeroes of $H(z)$ influencing $|H(e^{j\omega})|$
- Causality and Stability (All poles inside the Unit circle)
- Real-Value Coefficient(Conjugate-Pair Zeroes and poles)

## Discussion
To create a low-pass (LP) filter, where should a single pole be placed to allow only low-frequency components to pass through, considering the poles angular frequency?


