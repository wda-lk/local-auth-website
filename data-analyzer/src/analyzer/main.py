import analyzer.client.googlesheet as sheet_client

from logger import configure_logger
from loguru import logger as log

# Configure logs
configure_logger()

# Configure clients
sheet_client.configure()
sheet_client.inquire_sheet_data()
