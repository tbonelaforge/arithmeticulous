all: clean

clean:
	find ./ -name *.class | xargs rm
	find ./ -name *~ | xargs rm
