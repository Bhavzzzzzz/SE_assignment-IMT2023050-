## Multi-stage Dockerfile for Java CLI Calculator
# Build stage: compile .java sources
FROM openjdk:21-jdk AS build
WORKDIR /workspace
COPY . /workspace

RUN mkdir -p out \
    && javac -d out *.java

# Runtime stage: lean runtime image
FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /workspace/out /app/out

# When container runs without arguments, start the Calculator CLI
ENTRYPOINT ["java", "-cp", "/app/out"]
CMD ["Calculator"]

# Example usage:
# Build: docker build -t youruser/your-repo:tag .
# Run CLI: docker run --rm -it youruser/your-repo:tag
# Run tests: docker run --rm youruser/your-repo:tag AppTest
