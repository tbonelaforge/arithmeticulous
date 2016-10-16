all: clean app

clean:
	find ./ -name *.class | xargs rm
	find ./ -name *~ | xargs rm
	rm -f app

app:
	cd ./src; make
	@echo "Writing shortcut script app..."
	@echo '#! /bin/sh' > app
	@echo 'java -cp ./src arithmeticulous.ArithmeticApp "$$@"' >> app
	@chmod +x app
