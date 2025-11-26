# Build stage: Use Eclipse Temurin JDK for compiling
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY . /workspace

RUN mkdir -p out \
    && javac -d out *.java

# Runtime stage: Use Eclipse Temurin JRE (lighter) for running
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/out /app/out

# When container runs without arguments, start the Calculator CLI
ENTRYPOINT ["java", "-cp", "/app/out"]
CMD ["Calculator"]