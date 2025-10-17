#!/usr/bin/env python3
import os
import glob

model_dir = os.path.join(os.path.dirname(__file__), "src", "main", "resources", "assets", "charmncraft", "models", "block")

if os.path.isdir(model_dir):
    files_fixed = 0
    for json_file in glob.glob(os.path.join(model_dir, "*.json")):
        try:
            with open(json_file, 'r', encoding='utf-8') as f:
                content = f.read()
            
            if 'casualness_delight' in content:
                new_content = content.replace('casualness_delight', 'charmncraft')
                with open(json_file, 'w', encoding='utf-8') as f:
                    f.write(new_content)
                print(f"FIXED: {os.path.basename(json_file)}")
                files_fixed += 1
        except Exception as e:
            print(f"ERROR: {os.path.basename(json_file)}: {e}")
    
    print(f"\nTotal files fixed: {files_fixed}")
else:
    print(f"ERROR: Model directory not found at {model_dir}")
