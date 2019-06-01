FROM openjdk:8u212-jre

WORKDIR /app

RUN curl -LO http://ftp.jaist.ac.jp/pub/apache/spark/spark-2.4.3/spark-2.4.3-bin-hadoop2.7.tgz
RUN tar xvfz spark-2.4.3-bin-hadoop2.7.tgz -C /opt/ && rm -f spark-2.4.3-bin-hadoop2.7.tgz
RUN ln -s /opt/spark-2.4.3-bin-hadoop2.7 /opt/spark
RUN curl -L -o /opt/spark/jars/postgresql-42.2.5.jar https://jdbc.postgresql.org/download/postgresql-42.2.5.jar
RUN echo 'export SPARK_HOME=/opt/spark' >> ~/.bashrc
RUN echo 'export PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin' >> ~/.bashrc
