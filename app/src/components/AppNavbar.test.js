import { render, screen } from '@testing-library/react';
import AppNavbar from './AppNavbar';
import { BrowserRouter } from 'react-router-dom'

describe('AppNavbar', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('renders application navigation bar successfully', () => {
      render(<BrowserRouter><AppNavbar/></BrowserRouter>);
      expect(screen.getByText(/Home/i)).toBeInTheDocument();
      expect(screen.getByText(/GitHub/i)).toBeInTheDocument();
    });
});