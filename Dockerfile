FROM openjdk:8u212-jre

WORKDIR /app

RUN curl -LO http://ftp.jaist.ac.jp/pub/apache/spark/spark-2.4.3/spark-2.4.3-bin-hadoop2.7.tgz
RUN tar xvfz spark-2.4.3-bin-hadoop2.7.tgz -C /opt/ && rm -f spark-2.4.3-bin-hadoop2.7.tgz
RUN ln -s /opt/spark-2.4.3/spark-2.4.3-bin-hadoop2.7.tgz /opt/spark
RUN echo 'exprort SPARK_HOME=/opt/spark' >> .bash_profile
RUN echo 'exprort PATH=$PATH:$SPARK_HOME/bin:$SPARK_HOME/sbin' >> .bash_profile
