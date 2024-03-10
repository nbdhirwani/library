import { render, screen } from '@testing-library/react';
import BookList from './BookList';
import { BrowserRouter } from 'react-router-dom';

describe('BookList', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('renders book list screen successfully', () => {
      render(<BrowserRouter><BookList/></BrowserRouter>);
      expect(screen.getByText(/Loading.../i)).toBeInTheDocument();
    });
});