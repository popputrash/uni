
duration = 3;
Fs = 44100;
scale = 0.2;
recorder = audiorecorder(Fs, 16, 1);
recordblocking(recorder, duration);
audio = recorder.getaudiodata();
%recorder.play();

N = length(audio);
t = (0:N-1)/ Fs;

noise = scale * randn(N, 1);
noise_signal = audio + noise;

fft_c = fft(audio);

subplot(4, 1, 1);
plot(audio);
subplot(4, 1, 2);
plot(abs(fft_c));

fft_n = fft(noise_signal);
subplot(4, 1, 3);
plot(noise_signal);
subplot(4, 1, 4);
plot(abs(fft_n));

sound(noise_signal, Fs, 16);


