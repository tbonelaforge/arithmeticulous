all: clean app_shortcut

clean:
	find ./ -name *.class | xargs rm
	find ./ -name *~ | xargs rm
	rm -f app

app_shortcut:
	cd ./src; make
	@echo "Writing shortcut script app..."
	@echo '#! /bin/sh' > app
	@echo 'java -cp ./src arithmeticulous.ArithmeticApp "$$@"' >> app
	@chmod +x app
