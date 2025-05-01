# frontend/Dockerfile
FROM node:18
WORKDIR /twitter-client
COPY package*.json ./
RUN npm install
COPY . .
CMD ["npm", "run", "dev"]