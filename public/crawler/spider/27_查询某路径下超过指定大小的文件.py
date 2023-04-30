import os
import sys

def find_large_files(path, size_threshold):
    for dirpath, dirnames, filenames in os.walk(path):
        for filename in filenames:
            file_path = os.path.join(dirpath, filename)
            if os.path.getsize(file_path) > size_threshold:
                print(file_path)

# Example usage: find files larger than 20MB in the current directory and its subdirectories
find_large_files("E:\develop\gitee.com\source\spark", 20 * 1024 * 1024) # 20MB in bytes

