import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://wil.app.br:3003/api'
});

export default instance;
