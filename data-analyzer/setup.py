# -*- coding: utf-8 -*-
from setuptools import setup, find_packages

VERSION = "1.0.0.dev"


def get_requirements():
    with open("requirements.txt") as fp:
        return [x.strip() for x in fp.read().split("\n") if not x.startswith("#")]


install_requires = get_requirements()

setup(
        name = "data-analyzer",
        version = VERSION,
        long_description_content_type = "text/markdown",
        author = "Yohan Avishke",
        classifiers = [  # Optional
            "Development Status :: 2 - Development/Stable",
            "Intended Audience :: Developers",
            "License :: BSD 2-Clause License",
            "Programming Language :: Python :: 3.10.3",
            ],
        package_dir = {"": "src"},
        packages = find_packages("src"),
        python_requires = ">=3.10",
        install_requires = install_requires
        )
