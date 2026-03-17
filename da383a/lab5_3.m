[y_orig, Fs] = audioread('ekg.mp4');
y_orig = y_orig(1:20000);
subplot(4, 1, 1);
plot(y_orig);
y = y_orig + rand(1,20000) * 0.5 - 0.25;
subplot(4, 1, 2);
plot(y);

dft_y_orig = c_dft(y_orig);
subplot(4, 1, 3);
plot(abs(dft_y_orig));

dft_y = c_dft(y);
subplot(4, 1, 4);
plot(abs(dft_y));

function X = c_dft(x)
    N = length(x);
    X = zeros(1, N);
    for k = 0:N-1
        for n = 0:N-1
            X(k+1) = X(k+1) + x(n+1) * exp(-1j * 2 * pi * k * n / N);
        end
    end
end
