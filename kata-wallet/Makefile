all: lint test

test:
	pytest-3 -m "not slowtest"

full-test:
	pytest-3 

lint:
	pycodestyle .
	pylint3 *.py
