import { useState, useEffect, useRef } from "react";
import axios from "axios";

interface InvoiceSelectorProps {
  value: string;
  onChange: (val: string) => void;
  placeholder?: string;
}

export function InvoiceSelector({ value, onChange, placeholder }: InvoiceSelectorProps) {
  const [allNumbers, setAllNumbers] = useState<string[]>([]);
  const [isOpen, setIsOpen] = useState(false);
  const [filteredNumbers, setFilteredNumbers] = useState<string[]>([]);
  const containerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const fetchNumbers = async () => {
      try {
        const response = await axios.get<string[]>("http://localhost:8080/api/invoice/all-numbers");
        setAllNumbers(response.data);
        setFilteredNumbers(response.data);
      } catch (e) {
        console.error("Failed to fetch invoice numbers", e);
      }
    };
    fetchNumbers();
  }, []);

  useEffect(() => {
    if (!value) {
      setFilteredNumbers(allNumbers);
    } else {
      setFilteredNumbers(
        allNumbers.filter((num) =>
          num.toLowerCase().includes(value.toLowerCase())
        )
      );
    }
  }, [value, allNumbers]);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (containerRef.current && !containerRef.current.contains(event.target as Node)) {
        setIsOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  return (
    <div className="invoice-selector-container" ref={containerRef}>
      <input
        type="text"
        placeholder={placeholder || "Enter/Select Invoice Number..."}
        value={value}
        onChange={(e) => {
          onChange(e.target.value);
          setIsOpen(true);
        }}
        onFocus={() => setIsOpen(true)}
        className="form-input"
        autoComplete="off"
      />
      {isOpen && filteredNumbers.length > 0 && (
        <ul className="dropdown-options">
          {filteredNumbers.map((num) => (
            <li
              key={num}
              onClick={() => {
                onChange(num);
                setIsOpen(false);
              }}
              className="dropdown-item"
            >
              {num}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
