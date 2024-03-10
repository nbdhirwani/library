import { render, screen } from '@testing-library/react';
import Home from './Home';
import { BrowserRouter } from 'react-router-dom'

describe('Home', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('renders home screen successfully', () => {
      render(<BrowserRouter><Home/></BrowserRouter>);
      expect(screen.getByText(/Manage Books/i)).toBeInTheDocument();
    });
});