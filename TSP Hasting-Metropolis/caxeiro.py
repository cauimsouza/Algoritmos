#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Sat Feb 17 15:14:42 2018

@author: cauim
"""

import numpy as np
import matplotlib.pyplot as plt
from scipy.special import expit

lim_inf = 0
lim_sup = 100
n = 20
T = 100
T0 = 100

x = np.random.randint(lim_inf, lim_sup, size=n)
y = np.random.randint(lim_inf, lim_sup, size=n)

def V(s):
    total_sum = 0
    for i in range(len(s)-1):
        total_sum += np.hypot(x[s[i]] - x[s[i+1]], y[s[i]] - y[s[i+1]])
    total_sum += np.hypot(x[s[0]] - x[s[-1]], y[s[0]] - y[s[-1]])
    return total_sum
            
def R(s, i, j):
    t = s.copy()
    t[i], t[j] = t[j], t[i]
    return expit((V(s)-V(t))/T)


s = list(range(n))
np.random.shuffle(s)

n_iter = 100000
it = 0
while it < n_iter:
    i, j = np.random.randint(0, n, 2)
    u = np.random.rand()
    if u < R(s, i, j):
        s[i], s[j] = s[j], s[i]

    it += 1
    T =  T0 / np.log(it+1)


plt.plot(x[s + [s[0]]] , y[s + [s[0]]], color='b', linestyle='-', linewidth=2)
plt.show()