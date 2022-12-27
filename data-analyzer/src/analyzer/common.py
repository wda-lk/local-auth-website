import json

from loguru import logger as log


def write_to_json(path, data):
    """ Save data to a JSON file.
    :param path: string, file path.
    :param data: dict, data.
    """
    log.debug("Save JSON file to {}.", path)
    with open(path, "w") as file:
        json.dump(data, file)
