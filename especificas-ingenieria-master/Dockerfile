FROM node:16-alpine
RUN apk add --no-cache nano
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
CMD [ "npm", "start" ]