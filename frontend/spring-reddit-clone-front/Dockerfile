FROM node:14.16.0-alpine3.13 as node
# RUN addgroup app && adduser -S -G app app
# USER app
WORKDIR /app
COPY package*.json ./

RUN npm install -g @angular/cli @angular-devkit/build-angular && npm install

EXPOSE 4201

CMD ["npm", "start"]
# COPY . .
# RUN npm install
# RUN npm run build --prod

# FROM nginx:1.17.1-alpine
# COPY --from=node /app/dist/angular-reddit-clone /usr/share/nginx/html

