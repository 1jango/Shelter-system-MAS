import React from 'react';

const SystemRejection = ({ onConfirm }) => {
  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', flex: 1, paddingTop: '20px' }}>
      <div style={{ width: '520px', border: '1px solid #fca5a5', borderRadius: '12px', overflow: 'hidden', boxShadow: '0 10px 15px -3px rgba(0, 0, 0, 0.05)' }}>
        <div style={{ backgroundColor: '#ef4444', color: '#fff', padding: '16px', fontWeight: '600', fontSize: '15px', textAlign: 'center' }}>Wykryto blokadę konta</div>
        <div style={{ padding: '32px', backgroundColor: '#fff', textAlign: 'center' }}>
          <h3 style={{ color: '#991b1b', margin: '0 0 12px 0', fontSize: '20px', fontWeight: '700', letterSpacing: '-0.5px' }}>Klient znajduje się na czarnej liście.</h3>
          <p style={{ color: '#6b7280', fontSize: '14px', margin: '0 0 32px 0', fontWeight: '500' }}>Wniosek nie może być przetworzony.</p>
          <button
            onClick={onConfirm}
            style={{ backgroundColor: '#dc2626', color: '#fff', padding: '14px', border: 'none', borderRadius: '8px', cursor: 'pointer', fontWeight: '600', width: '100%', fontSize: '14px' }}
          >
            Generuj automatyczną odmowę
          </button>
        </div>
      </div>
    </div>
  );
};

export default SystemRejection;