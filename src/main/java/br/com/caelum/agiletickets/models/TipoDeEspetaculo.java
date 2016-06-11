package br.com.caelum.agiletickets.models;

public enum TipoDeEspetaculo {
	
	CINEMA {
		@Override
		public double getTaxa() {
			return 0.10;
		}
	}, SHOW {
		@Override
		public double getTaxa() {
			return 0.10;
		}
	}, TEATRO {
		@Override
		public double getTaxa() {
			return 0;
		}
	}, BALLET {
		@Override
		public double getTaxa() {
			return 0.20;
		}
	}, ORQUESTRA {
		@Override
		public double getTaxa() {
			return 0.20;
		}
	};
	
	public abstract double getTaxa();
}
