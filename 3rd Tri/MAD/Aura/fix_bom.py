#!/usr/bin/env python3
import os
import sys

res_path = r"D:\Sx Varun\Sx Code\MCA\3rd Tri\MAD\Aura\app\src\main\res"
fixed = 0

for root, dirs, files in os.walk(res_path):
    for fname in files:
        if fname.endswith('.xml'):
            fpath = os.path.join(root, fname)
            with open(fpath, 'rb') as f:
                data = f.read()
            if data[:3] == b'\xef\xbb\xbf':
                with open(fpath, 'wb') as f:
                    f.write(data[3:])
                print(f"Fixed: {fpath}")
                fixed += 1
            elif data[:2] in (b'\xff\xfe', b'\xfe\xff'):
                # UTF-16 BOM - re-encode as UTF-8 no BOM
                encoding = 'utf-16-le' if data[:2] == b'\xff\xfe' else 'utf-16-be'
                text = data[2:].decode(encoding)
                with open(fpath, 'wb') as f:
                    f.write(text.encode('utf-8'))
                print(f"Fixed UTF-16: {fpath}")
                fixed += 1

print(f"Total fixed: {fixed}")

