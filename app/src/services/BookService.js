import api from './api';

export const getAllBooks = async (sortField, sortDirection) => {
    return api.get(`api/books?sortField=${sortField}&sortDirection=${sortDirection}`);
};

export const getOneBook = async (id) => {
    return api.get(`/api/book/${id}`);
};

export const deleteBook = async (id) => {
    return api.delete(`/api/book/${id}`, {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    })
};

export const updateBook = async (id, payload) => {
    return api.put(`/api/book/${id}`, JSON.stringify(payload), {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
    });
};

export const createBook = async (payload) => {
    return api.post('/api/book', JSON.stringify(payload), {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    });
};