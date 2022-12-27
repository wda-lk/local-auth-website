import logging
import os

from loguru import logger as log

LOG_LEVEL = logging.INFO

# Paths
ROOT_DIR = os.path.abspath(os.path.dirname(os.path.dirname(os.path.dirname(__file__))))
SOURCE_DIR = f"{ROOT_DIR}/src/analyzer"
DATA_DIR = f"{ROOT_DIR}/data"
