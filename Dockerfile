# 依赖jdk8环境
FROM openjdk:8

# 对外暴露80
EXPOSE 80
#执行命令
RUN bash -c  'touch /app.jar'