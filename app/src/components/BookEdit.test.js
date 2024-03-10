import { render, screen } from '@testing-library/react';
import BookEdit from './BookEdit';
import { BrowserRouter } from 'react-router-dom'
describe('BookEdit', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('renders book edit screen successfully', () => {
      render(<BrowserRouter><BookEdit/></BrowserRouter>);
      expect(screen.getByText(/Add Book/i)).toBeInTheDocument();
      expect(screen.getByText(/Name/i)).toBeInTheDocument();
      expect(screen.getByText(/Author/i)).toBeInTheDocument();
      expect(screen.getByText(/Save/i)).toBeInTheDocument();
      expect(screen.getByText(/Cancel/i)).toBeInTheDocument();
    });
});