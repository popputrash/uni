x = [1, 2, 3, 4];
N = length(x);
n = 0:N-1;
subplot(3, 1, 1);
stem(n,x, 'filled');
title('insignal');
grid on;

X = c_dft(x);
k = 0:N-1;
subplot(3,1,2);
stem(k, abs(X), 'filled');
title('dft');
grid on;

x_inv = c_idft(X);
k = 0:N-1;
subplot(3,1,3);
stem(k, x_inv, 'filled');
title('idft');
grid on;


function X = c_dft(x)
    N = length(x);
    X = zeros(1, N);
    for k = 0:N-1
        for n = 0:N-1
            X(k+1) = X(k+1) + x(n+1) * exp(-1j * 2 * pi * k * n / N);
        end
    end
end

function x_inv = c_idft(X)
    N = length(X);
    x_inv = zeros(1, N);
    for k = 0:N-1
        for n = 0:N-1
            x_inv(k+1) = x_inv(k+1) + (1/N)*(X(n+1) * exp(1j * 2 * pi * k * n / N));
        end
    end
end
