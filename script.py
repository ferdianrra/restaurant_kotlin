import time
import subprocess

PACKAGE = "com.ferdi.restaurankotlin"
INTERVAL = 0.3
DURATION = 10
TOTAL_CORES = 4
END_TIME = time.time() + DURATION

samples = []

while time.time() < END_TIME:
    now = time.strftime("%H:%M:%S")
    result = subprocess.run(["adb", "shell", "top", "-n", "1", "-b"], stdout=subprocess.PIPE, text=True)
    lines = result.stdout.splitlines()
    cpu_usage = 0
    mem_usage = 0
    for line in lines:
        if PACKAGE in line:
            parts = line.split()
            print("DEBUG LINE:", parts)
            try:
                cpu_usage = float(parts[8]) / TOTAL_CORES  # normalize %CPU
                rss = parts[5]  # e.g., "315M"
                if rss.endswith("M"):
                    mem_usage = float(rss[:-1])  # only take number
            except:
                cpu_usage = 0
                mem_usage = 0
            break
    samples.append((now, cpu_usage, mem_usage))
    time.sleep(INTERVAL)

# Save to CSV
with open("cpu_mem_log.csv", "w") as f:
    f.write("Timestamp,CPU_Usage(%),Memory_Usage(MB)\n")
    for ts, cpu, mem in samples:
        f.write(f"{ts},{cpu:.2f},{mem:.2f}\n")

# Averages
cpu_avg = sum(cpu for _, cpu, _ in samples) / len(samples)
mem_avg = sum(mem for _, _, mem in samples) / len(samples)
print(f"Average CPU Usage: {cpu_avg:.2f}%")
print(f"Average Memory Usage: {mem_avg:.2f} MB")
