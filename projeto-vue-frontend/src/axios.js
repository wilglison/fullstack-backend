import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://10.15.254.21:3001/api'
});

export default instance;
