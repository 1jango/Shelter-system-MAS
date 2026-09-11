import React from 'react';

const ClientForm = ({ clientMetraz, setClientMetraz, clientZwierzeta, setClientZwierzeta, onSubmit }) => {
  return (
    <div style={{ display: 'flex', justifyContent: 'center', flex: 1, alignItems: 'center' }}>
      <div style={{ width: '580px', border: '1px solid #e5e7eb', borderRadius: '12px', overflow: 'hidden', boxShadow: '0 10px 15px -3px rgba(0, 0, 0, 0.05)' }}>
        <div style={{ backgroundColor: '#374151', color: '#fff', padding: '16px', fontWeight: '600', fontSize: '15px' }}>Portal Klienta - Powiadomienie</div>
        <div style={{ padding: '32px', backgroundColor: '#fff' }}>
          <div style={{ backgroundColor: '#fffbe6', color: '#d97706', padding: '14px 18px', borderRadius: '8px', marginBottom: '28px', fontSize: '14px', borderLeft: '4px solid #f59e0b', fontWeight: '500' }}>
            <strong style={{ color: '#b45309', display: 'block', marginBottom: '4px' }}>Twoja ankieta przedadopcyjna zawiera braki formalne.</strong>
            Proszę uzupełnić brakujące pola.
          </div>

          <div style={{ marginBottom: '20px' }}>
            <label style={{ display: 'block', fontWeight: '600', marginBottom: '8px', fontSize: '14px', color: '#374151' }}>Metraż mieszkania</label>
            <input
              type="number"
              value={clientMetraz}
              onChange={(e) => setClientMetraz(e.target.value)}
              style={{ width: '100%', padding: '12px 14px', border: '1px solid #d1d5db', borderRadius: '8px', boxSizing: 'border-box', fontSize: '14px', outline: 'none' }}
              placeholder="np. 45m²"
            />
          </div>

          <div style={{ marginBottom: '32px' }}>
            <label style={{ display: 'block', fontWeight: '600', marginBottom: '8px', fontSize: '14px', color: '#374151' }}>Czy posiadasz inne zwierzęta?</label>
            <select
              value={clientZwierzeta}
              onChange={(e) => setClientZwierzeta(e.target.value === 'true')}
              style={{ width: '100%', padding: '12px 14px', border: '1px solid #d1d5db', borderRadius: '8px', fontSize: '14px', backgroundColor: '#fff', outline: 'none' }}
            >
              <option value="false">Brak</option>
              <option value="true">Tak</option>
            </select>
          </div>

          <div style={{ display: 'flex', justifyContent: 'center' }}>
            <button
              onClick={onSubmit}
              style={{ backgroundColor: '#4b5563', color: '#fff', padding: '14px 32px', border: 'none', borderRadius: '8px', cursor: 'pointer', fontWeight: '600', fontSize: '14px', width: '100%', boxShadow: '0 1px 2px rgba(0,0,0,0.05)' }}
            >
              Zapisz i wyślij ponownie
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ClientForm;