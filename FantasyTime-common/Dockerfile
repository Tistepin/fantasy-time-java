# 基础镜像
FROM java:8
# 环境变量
ENV APP_HOME=/mydata/java
# 创建容器默认进入的目录
WORKDIR $APP_HOME
# 复制jar包到容器中
COPY ./FantasyTime-common-1.0-SNAPSHOT.jar ./FantasyTime-common-1.0-SNAPSHOT.jar
# 暴露端口
EXPOSE 8004
# 启动命令
ENTRYPOINT ["java","-jar","-Xms100m","-Xmx100m"]
CMD ["service_cms.jar"]