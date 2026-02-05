import math


a = 2;
for i in range(82):
    a = math.cos(4 * a)

print(a)

tot = 0
for i in range(1,100):
    tot += math.cbrt(i)

print(tot)


def p_n(x, n):
    return sum(((-1)**k)* x**(2*k) / math.factorial(2*k) 
for k in range(n+1))

x = 4
tol = 0.001
n = 0
while True:
    error = abs(p_n(x, n) - math.cos(x))
    if error < tol:
        break
    n += 1
#print(p_n(9/4, 9))
print(n)

def f_x(x):
    return math.tan(x) + 1/5


a = -0.5
b = 0
m = (a+b)/2


for i in range(1, 10):
    ar = f_x(a)
    br = f_x(b)
    r = f_x(m)
    if((r < 0.0 and ar < 0.0) or (r > 0.0 and ar > 0.0)):
        a = m
    else:
        b = m
    
    m = (a+b)/2

if(math.fabs(f_x(a)) > math.fabs(f_x(b))):
    print(f_x(b))
else:
    print(f_x(a))


def g(x):
    return 2*(x**3) - 4

x0 = 0
x1 = 2.5

steg = 11
for i in range(steg):
    g_x0 = g(x0)
    g_x1 = g(x1)

    if(math.fabs(g_x1 - g_x0) < 1e-14):
        print(f"Varning: Nämnaren är nästan noll! Avbrott i steg {i}")
        break;
    x2 = x0 - g_x0 * (x1 - x0)/(g_x1 - g_x0)

    print(f"x_{i+2} = {x2:.10f},  f(x_{i+2}) = {g(x2):.6e}")
    
    x0 = x1
    x1 = x2
