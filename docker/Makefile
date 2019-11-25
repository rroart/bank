VERSION = 0.1-SNAPSHOT
SUBDIRS = tensorflow spark pd eureka web iclij-web core iclij-core

all: $(SUBDIRS)

core:
	cd $@ && mkdir -p files && ln -f ../../key/private.p12 files
	cd $@ && mkdir -p files && ln -f ../../main/core/target/catwarebank-main-core-${VERSION}.jar files && docker build -t="catwarebank-$@" .

.PHONY: all $(SUBDIRS)
