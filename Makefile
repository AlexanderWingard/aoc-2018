.PHONY: all

all: lein

lein:
	curl -OL https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein 
	chmod a+x lein
