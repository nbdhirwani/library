import { render, screen } from '@testing-library/react';
import BookList from './BookList';
import { BrowserRouter } from 'react-router-dom';
// import bookService from '../services/BookService';

// jest.mock('../services/BookService');

describe('BookList', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('renders book list screen successfully', () => {
      render(<BrowserRouter><BookList/></BrowserRouter>);
      expect(screen.getByText(/Loading.../i)).toBeInTheDocument();
    });
/*
    test('renders book list screen successfully', () => {
      let mockedFunction = jest.fn();
      bookService.getAllBooks = mockedFunction.mockResolvedValue({data: []});

      render(<BrowserRouter><BookList/></BrowserRouter>);
      expect(screen.getByText(/All Books/i)).toBeInTheDocument();
      expect(screen.getByText(/Add Book/i)).toBeInTheDocument();
      expect(screen.getByText(/Sort By/i)).toBeInTheDocument();
      expect(screen.getByText(/Default/i)).toBeInTheDocument();
      expect(screen.getByText(/Name Ascending/i)).toBeInTheDocument();
      expect(screen.getByText(/Name Descending/i)).toBeInTheDocument();
      expect(screen.getByText(/Author Name Ascending/i)).toBeInTheDocument();
      expect(screen.getByText(/Author Name Descending/i)).toBeInTheDocument();
      expect(screen.getByText(/Name/i)).toBeInTheDocument();
      expect(screen.getByText(/Author/i)).toBeInTheDocument();
      expect(screen.getByText(/Status/i)).toBeInTheDocument();
      expect(screen.getByText(/Actions/i)).toBeInTheDocument();
    });
*/
});