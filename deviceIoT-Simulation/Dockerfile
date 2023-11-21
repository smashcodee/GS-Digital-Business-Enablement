FROM node:14

WORKDIR /usr/src/app/deviceIoT

COPY package*.json ./

RUN npm install

COPY . .

CMD ["node", "iot.js"]