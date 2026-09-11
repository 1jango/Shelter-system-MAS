import React from 'react';

const ManualVerification = ({ selectedApp, chkFormal, setChkFormal, chkManual, setChkManual, onBack, onReject, onAccept, onGoToClient }) => {
  return (
    <div style={{ display: 'flex', flexDirection: 'column', height: '100%', justifyContent: 'space-between', flex: 1 }}>
      <div>
        <h3 style={{ backgroundColor: '#374151', color: '#fff', padding: '14px 20px', margin: '0 0 24px 0', fontSize: '16px', fontWeight: '600', borderRadius: '8px' }}>Formularz weryfikacji manualnej (Szczegóły wniosku)</h3>

        <div style={{ display: 'flex', gap: '64px', padding: '0 8px' }}>
          <div style={{ flex: 1 }}>
            <h4 style={{ color: '#111827', borderBottom: '2px solid #f3f4f6', paddingBottom: '8px', margin: '0 0 16px 0', fontSize: '14px', fontWeight: '700', textTransform: 'uppercase', letterSpacing: '0.5px' }}>Dane wniosku</h4>
            <p style={{ margin: '12px 0', fontSize: '14px', color: '#4b5563' }}><strong style={{ color: '#111827' }}>Ankiety Przedadopcyjne (historia wypełnień):</strong></p>
            <div style={{ border: '1px solid #e5e7eb', borderRadius: '8px', overflow: 'hidden', marginBottom: '20px' }}>
              <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left', fontSize: '13px' }}>
                <thead>
                  <tr style={{ backgroundColor: '#f9fafb', borderBottom: '1px solid #e5e7eb' }}>
                    <th style={{ padding: '8px 12px', color: '#4b5563', fontWeight: '600' }}>Wersja</th>
                    <th style={{ padding: '8px 12px', color: '#4b5563', fontWeight: '600' }}>Metraż</th>
                    <th style={{ padding: '8px 12px', color: '#4b5563', fontWeight: '600' }}>Inne zwierzęta</th>
                  </tr>
                </thead>
                <tbody>
                  {(selectedApp.preAdoptionForms || []).map((form) => (
                    <tr key={form.id} style={{ borderBottom: '1px solid #f3f4f6' }}>
                      <td style={{ padding: '8px 12px', color: '#374151' }}>#{form.version}</td>
                      <td style={{ padding: '8px 12px', fontWeight: '600', color: '#111827' }}>{form.apartmentSize}m</td>
                      <td style={{ padding: '8px 12px', color: '#374151' }}>{form.hasOtherAnimals ? 'Tak' : 'Brak'}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
            <p style={{ margin: '12px 0', fontSize: '14px', color: '#4b5563' }}><strong style={{ color: '#111827' }}>Zwierze:</strong> <span style={{ fontWeight: '600', color: '#2563eb' }}>{selectedApp.animalName}</span></p>
          </div>

          <div style={{ flex: 1 }}>
            <h4 style={{ color: '#111827', borderBottom: '2px solid #f3f4f6', paddingBottom: '8px', margin: '0 0 16px 0', fontSize: '14px', fontWeight: '700', textTransform: 'uppercase', letterSpacing: '0.5px' }}>Weryfikacja manualna (Szczegóły wniosku)</h4>

            <div style={{ margin: '16px 0', display: 'flex', alignItems: 'center', gap: '12px', backgroundColor: '#f9fafb', padding: '12px 16px', borderRadius: '8px', border: '1px solid #e5e7eb' }}>
              <input type="checkbox" id="chkFormal" checked={chkFormal} onChange={(e) => setChkFormal(e.target.checked)} style={{ width: '18px', height: '18px', cursor: 'pointer', accentColor: '#4b5563' }} />
              <label htmlFor="chkFormal" style={{ cursor: 'pointer', fontSize: '14px', fontWeight: '500', color: '#374151' }}>Czy kryteria formalne są akceptowalne?</label>
            </div>

            <div style={{ margin: '16px 0', display: 'flex', alignItems: 'center', gap: '12px', backgroundColor: '#f9fafb', padding: '12px 16px', borderRadius: '8px', border: '1px solid #e5e7eb' }}>
              <input type="checkbox" id="chkManual" checked={chkManual} onChange={(e) => setChkManual(e.target.checked)} style={{ width: '18px', height: '18px', cursor: 'pointer', accentColor: '#4b5563' }} />
              <label htmlFor="chkManual" style={{ cursor: 'pointer', fontSize: '14px', fontWeight: '500', color: '#374151' }}>Czy wynik weryfikacji manualnej jest pozytywny?</label>
            </div>
          </div>
        </div>
      </div>

      <div style={{ display: 'flex', justifyContent: 'center', gap: '16px', marginTop: '48px' }}>
        <button onClick={onBack} style={{ backgroundColor: '#ffffff', color: '#4b5563', padding: '12px 28px', border: '1px solid #d1d5db', borderRadius: '8px', cursor: 'pointer', fontSize: '14px', fontWeight: '600' }}>Powrót</button>
        <button onClick={onGoToClient} style={{ backgroundColor: '#f59e0b', color: '#ffffff', padding: '12px 28px', border: 'none', borderRadius: '8px', cursor: 'pointer', fontSize: '14px', fontWeight: '600' }}>Wyślij powiadomienie o brakach do klienta</button>
        <button onClick={onReject} style={{ backgroundColor: '#ffffff', color: '#b91c1c', padding: '12px 28px', border: '1px solid #fca5a5', borderRadius: '8px', cursor: 'pointer', fontSize: '14px', fontWeight: '600' }}>Odrzuć wniosek</button>
        <button
          onClick={onAccept}
          disabled={!chkFormal || !chkManual}
          style={{
            backgroundColor: (chkFormal && chkManual) ? '#4b5563' : '#e5e7eb',
            color: (chkFormal && chkManual) ? '#fff' : '#9ca3af',
            padding: '12px 32px', border: 'none', borderRadius: '8px',
            cursor: (chkFormal && chkManual) ? 'pointer' : 'not-allowed',
            fontSize: '14px', fontWeight: '600', boxShadow: '0 1px 2px rgba(0,0,0,0.05)'
          }}
        >
          Zatwierdź wniosek
        </button>
      </div>
    </div>
  );
};

export default ManualVerification;