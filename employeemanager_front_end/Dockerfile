# Stage 1: Build the Angular application
FROM node:16 AS build
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Stage 2: Serve the application with nginx
FROM nginx:alpine
COPY --from=build /usr/src/app/dist/crud-app /usr/share/nginx/html