#!/usr/bin/env python
# -*- coding: utf-8 -*-

import argparse
import os
import shutil

N = 2  # the number of files in seach subfolder folder

def move_files(abs_dirname):
    """Move files into subdirectories."""
    total_size = 0L
    sub_size = 0L
    
    files = [os.path.join(abs_dirname, f) for f in sorted(os.listdir(abs_dirname))]
    total_size += sum([os.path.getsize(name) for name in files]) 

    i = 0
    subfolder_name = os.path.join(abs_dirname, "../split")
    subdir_name = os.path.join(subfolder_name, '{0:03d}'.format(0))
    os.makedirs(subdir_name)

    for f in files:
        print os.path.basename(f), subdir_name
        sub_size += os.path.getsize(f)
        if sub_size < (total_size / float(N-1)):
            # move file to current dir
            f_base = os.path.basename(f)
            shutil.copy(f, os.path.join(subdir_name, f_base))
        else:
            # create new subdir if necessary
            i += 1
            sub_size = 0
            subdir_name = os.path.join(subfolder_name, '{0:03d}'.format(i))
            os.mkdir(subdir_name)

            shutil.copy(f, os.path.join(subdir_name, f_base))
            sub_size += os.path.getsize(f)

def parse_args():
    """Parse command line arguments passed to script invocation."""
    parser = argparse.ArgumentParser(
        description='Split files into multiple subfolders.')

    parser.add_argument('src_dir', help='source directory')

    return parser.parse_args()


def main():
    args = parse_args()
    src_dir = args.src_dir

    if not os.path.exists(src_dir):
        raise Exception('Directory does not exist ({0}).'.format(src_dir))

    move_files(os.path.abspath(src_dir))

if __name__ == '__main__':
    main()